package com.example.modeljson.service;

import com.example.modeljson.model.Config;
import com.example.modeljson.repository.IConfigRepository;
import com.example.modeljson.service.interfaces.IRdbms2JsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class Rdbms2JsonServiceImpl implements IRdbms2JsonService {

    private final IConfigRepository configRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void storeConfigJson(@NonNull MultipartFile file) {
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            //traverse(null, objectMapper.readTree(reader));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObjectNode buildConfigJson() {
        // Only not deleted data
        List<Config> children = configRepository.findByParentNullAndDeletedFalse();
        return this.traverseBuild(children, objectMapper.createObjectNode());
    }

    //@Transactional
    private ObjectNode traverseBuild(List<Config> children, ObjectNode parent) {

        for (Config child : children) {

            String attributeName = child.getAttribute().getName();
            String attributeValue = child.getDefaultValue();
            String type = child.getAttribute().getAttributeType().getType();
            Boolean isList = child.getAttribute().getAttributeType().getIsList();

            // Check if it is a valid enum
            Boolean isEnum = child.getAttribute().getAttributeType().getIsEnum();


            if (isEnum) {
                // TODO: LOGIC
                // Check enum validation validateEnumValue(AttributeType type, String attributeValue)
                // else log error and continue
            }

            // Todo: big try/catch?
            switch (type) {
                case "String": {
                    createNode(parent, isList, attributeName, attributeValue, String::valueOf);
                    break;
                }
                case "Boolean": {
                    createNode(parent, isList, attributeName, attributeValue, Boolean::valueOf);
                    break;
                }
                case "Integer": {
                    createNode(parent, isList, attributeName, attributeValue, Integer::valueOf);
                    break;
                }
                case "Double": {
                    createNode(parent, isList, attributeName, attributeValue, Double::valueOf);
                }
                case "Object": {
                    try {
                        assert parent != null;
                        List<Config> currentChildren = configRepository.findByParentId(child.getId());
                        if (isList) {
                            ArrayNode nestedArray = parent.putArray(attributeName);
                            fillArrayObjectsNode(currentChildren, nestedArray); // fill with objects built with children configs

                        } else { // simple value
                            ObjectNode nestedNode = parent.putObject(attributeName);
                            this.traverseBuild(currentChildren, nestedNode);
                        }
                    } catch (Exception ex) {
                        log.error("*** Error while 'object' node creation: {}", ex.getMessage());
                    }
                    break;
                }
            }
        }

        return parent;
    }

    // Why use @NonNull

    /**
     * Make a list of object arrays
     *
     * @param children list of config elements with isList in attributeType checked
     * @param parent parent node
     */
    private void fillArrayObjectsNode(List<Config> children, ArrayNode parent) {
        ObjectNode objectNode = objectMapper.createObjectNode();

        try {
            assert parent != null;
            traverseBuild(children, objectNode);
            zipObjectNode(objectNode, parent);
        } catch (Exception ex) {
            log.error("Error creating array nested object: {}", ex.getMessage());
        }
    }

    /**
     * Zip elements, truncate values to min size of elements present
     *
     * @param objectNode object node
     * @param parent node
     */
    private void zipObjectNode(ObjectNode objectNode, ArrayNode parent) {

        var fields = objectNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            ArrayNode values = (ArrayNode) field.getValue();

            for (int i = 0; i < values.size(); i++) {
                if (parent.size() <= i) {
                    parent.add(objectMapper.createObjectNode().set(key, values.get(i)));
                } else {
                    ((ObjectNode) parent.get(i)).set(key, values.get(i));
                }
            };
            
        }
    }

    /**
     * Create and insert a node in parent object node
     *
     * <p>
     *     USAGE: createNode(parent, isList, attributeName, attributeValue, Double::valueOf);
     * </p>
     *
     * @param parent parent object node
     * @param isList {@code true} if it is a list node, otherwise false
     * @param attributeName tag name
     * @param attributeValue value of attribute as string
     * @param function function required for mapping values
     */
    private void createNode(ObjectNode parent,
                            Boolean isList,
                            String attributeName,
                            String attributeValue,
                            Function<String, Object> function) {
        try {
            // parent cant be null
            assert parent != null;

            String REGEXP = "\\p{javaSpaceChar}*,\\p{javaSpaceChar}*";

            if (isList) {
                ArrayNode nodeArray = parent.putArray(attributeName);
                var localCollection = Arrays.stream(attributeValue.strip().split(REGEXP)).map(function).collect(Collectors.toList());
                nodeArray.addAll((ArrayNode) objectMapper.valueToTree(localCollection));

            } else { // simple node value
                parent.put(attributeName, attributeValue);
            }

        } catch (Exception ex) {
            log.error("*** Error while 'simple' node creation: {}", ex.getMessage());
        }
    }


    /*private void traverse(Config parent, JsonNode root) {

        Iterator<Map.Entry<String, JsonNode>> children = root.fields();

        while(children.hasNext()) {

            Map.Entry<String, JsonNode> child = children.next();
            String attributeName = child.getKey();
            JsonNode defaultValue = child.getValue();

            if (defaultValue.isObject()) {

                Optional<Attribute> attributeOpt = attributeRepository.findByName(attributeName);

                Attribute attribute;
                // Create attribute
                if (attributeOpt.isEmpty()) {

                    AttributeType attrType = new AttributeType();
                    attrType.setType(Object.class.getSimpleName());

                    AttributeType attributeType = attributeTypeRepository.save(attrType);

                    Attribute attr = new Attribute();
                    attr.setName(attributeName);
                    attr.setAttributeType(attributeType);

                    attribute = attributeRepository.save(attr);
                } else {
                    attribute = attributeOpt.get();
                }


                Config config = new Config();
                config.setParent(parent);
                config.setAttribute(attribute);

                // store config
                configRepository.save(config);
                traverse(config, defaultValue);

            } else if (defaultValue.isArray()) {
                // TODO: ARRAY
                System.out.println("is Array");
                ArrayNode arrayNode = (ArrayNode) defaultValue;

                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode arrayElement = arrayNode.get(i);
                    System.out.println("ArrayElement[" + i + "] = " + arrayElement);
                }
            } else {
                // TODO: ---- same as Object
                Optional<Attribute> attributeOpt = attributeRepository.findByName(attributeName);

                Attribute attribute;
                // Create attribute
                if (attributeOpt.isEmpty()) {
                    AttributeType attrType = new AttributeType();
                    // TODO
                    attrType.setType("-----------------------------");

                    AttributeType attributeType = attributeTypeRepository.save(attrType);

                    Attribute attr = new Attribute();
                    attr.setName(attributeName);
                    attr.setAttributeType(attributeType);

                    attribute = attributeRepository.save(attr);
                } else {
                    attribute = attributeOpt.get();
                }


                Config config = new Config();
                config.setParent(parent);
                config.setAttribute(attribute);

                // store config
                configRepository.save(config);
                // TODO: ---- same as Object
            }
        }
    }

    private AttributeType getAttributeType(JsonNode node) {
        JsonNodeType fieldType  = node.getNodeType();



        //boolean isList = type == "List";
        //boolean isEnum = type == "List";

        return null;
    }


    private AttributeType getNodeType(JsonNode node) {
        String type;
        boolean isList = false;
        boolean isEnum = false;
        JsonNodeType fieldType  = node.getNodeType();

        switch(fieldType) {
            case OBJECT: {
                type = Object.class.getSimpleName();
                break;
            }
            case ARRAY: {
                type = List.class.getSimpleName();
                isList = true;
                break;
            }
            case BOOLEAN: {
                type = Boolean.class.getSimpleName();
                break;
            }
            case NUMBER: {
                if (node.isInt()) {
                    type = Integer.class.getSimpleName();
                } else if (node.isDouble()) {
                    type = Double.class.getSimpleName();
                }
                break;
            }
            case STRING: {
                Iterator<Map.Entry<String, JsonNode>> children = node.fields();

                while (children.hasNext()) {
                    System.out.println("Check getNodeType for enum value" + children.next().getValue());
                }

                type = String.class.getSimpleName();
                break;
            }
            default: type = null;
        }

        return new AttributeType();
    }*/
}
