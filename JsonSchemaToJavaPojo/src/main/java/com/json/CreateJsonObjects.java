package com.json;

import java.io.File;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class CreateJsonObjects {
	
	

	public static void main(String[] args) {
		try{

		JCodeModel codeModel = new JCodeModel();

		URL source = new URL("file:///C:/Users/Kalyan%20Pamula/Desktop/Json%20files/Survey.schema_v0_14a.json");

		GenerationConfig config = new DefaultGenerationConfig() {
			@Override
			public boolean isGenerateBuilders() { // set config option by
													// overriding method
				return true;
			}
		};

		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(), new SchemaStore()),
				new SchemaGenerator());
		mapper.generate(codeModel, "ClassName", "com.hendall.survey.schematopojo", source);

		codeModel.build(new File("C:/Users/Kalyan Pamula/OneDrive/Hendall_Workspace/JsonSchemaToJavaPojo/src/main/java"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
