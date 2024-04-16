package com.example.modeljson.service;

import com.example.modeljson.model.Config;
import com.example.modeljson.repository.IAttributeRepository;
import com.example.modeljson.repository.IAttributeTypeRepository;
import com.example.modeljson.repository.IAttributeTypeValueRepository;
import com.example.modeljson.repository.IConfigRepository;
import com.example.modeljson.service.interfaces.IRdbms2JsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class Rdbms2JsonServiceImpl implements IRdbms2JsonService {

    private final IConfigRepository configRepository;
    private final ObjectMapper objectMapper;
    private final IAttributeRepository attributeRepository;
    private final IAttributeTypeRepository attributeTypeRepository;
    private final IAttributeTypeValueRepository attributeTypeValueRepository;

    private final String REGEXP = "\\p{javaSpaceChar}*,\\p{javaSpaceChar}*";

    @Override
    public void storeConfigJson(@NonNull MultipartFile file) {

        InputStreamReader reader;
        JsonNode rootNode;

        try {
            ObjectMapper mapper = new ObjectMapper();
            reader = new InputStreamReader(file.getInputStream());
            rootNode = mapper.readTree(reader);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //traverse(null, rootNode);
    }

    @Override
    public ObjectNode buildConfigJson() {
        ObjectMapper mapper = new ObjectMapper();
        // Only not deleted data
        List<Config> children = configRepository.findByParentNullAndDeletedFalse();
        return this.traverseBuild(children, mapper.createObjectNode());
    }

    //@Transactional
    private ObjectNode traverseBuild(List<Config> children, ObjectNode parent) {

        for (Config child : children) {

            String attributeName = child.getAttribute().getName();
            String attributeValue = child.getDefaultValue();
            String type = child.getAttribute().getAttributeType().getType();
            Boolean isList = child.getAttribute().getAttributeType().getIsList();
            Boolean isEnum = child.getAttribute().getAttributeType().getIsEnum();

            // Todo: check for is list
            switch (type) {
                case "String": {
                    if (parent != null) {
                        if (isList) {
                            // do list stuff
                        } else if (isEnum) {
                            // do enum stuff
                        } else {
                            parent.put(attributeName, attributeValue);
                        }
                    }
                    break;
                }
                case "Boolean": {
                    if (parent != null) {
                        if (isList) {
                        // do list stuff
                        } else if (isEnum) {
                            // do enum stuff
                        } else {
                            parent.put(attributeName, Boolean.valueOf(attributeValue));
                        }
                    }
                    break;
                }
                case "Integer": {
                    if (parent != null) {
                        if (isList) {
                            // do list stuff
                        } else if (isEnum) {
                            // do enum stuff
                        } else {
                            parent.put(attributeName, Integer.valueOf(attributeValue));
                        }
                    }
                    break;
                }
                case "Double": {
                    if (parent != null) {
                        if (isList) {
                            // do list stuff
                        } else if (isEnum) {
                            // do enum stuff
                        } else {
                            parent.put(attributeName, Double.valueOf(attributeValue));
                        }
                    }
                    break;
                }
                case "List": {
                    if (parent != null) {
                        ArrayNode currArray  = parent.putArray(attributeName);

                        if (attributeValue != null && !attributeValue.isEmpty()) {
                            String[] arrValue = attributeValue.split(REGEXP);
                        } else {
//                            // get all children with isList = True
//                            ObjectMapper mapper = new ObjectMapper();
//
//                            List<Config> listChildren = configRepository.findByParentIdAndDeletedFalse(child.getId());
//                            //ArrayNode arrayNode = mapper.convertValue(listChildren, ArrayNode.class);
//                            ArrayNode arrayNode = new ArrayNode(JsonNodeFactory.instance);
//
//                            // traverse children
//                            ArrayNode nestedNode = parent.putArray(attributeName).addAll(arrayNode);
//                            this.traverseBuild(listChildren, mapper.valueToTree(nestedNode));
                        }
                    }
                }
                case "Object": {
                    if (parent != null && !isList) {
                        // Child is now parent
                        List<Config> currentChildren = configRepository.findByParentId(child.getId());
                        ObjectNode nestedNode = parent.putObject(attributeName);
                        this.traverseBuild(currentChildren, nestedNode);
                    }
                    break;
                }
                default: {
                    if (isList) {
                        System.out.println("Is List");
                    } else if (isEnum) {
                        System.out.println("Is Enum");

                    }
                }
            }
        }

        return parent;
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
