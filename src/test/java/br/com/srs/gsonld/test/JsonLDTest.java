package br.com.srs.gsonld.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class JsonLDTest {
    private ClassLoader classLoader;

    @Before
    public void init() {
        classLoader = Thread.currentThread().getContextClassLoader();
    }

    @Test
    public void JsonldMustSerialize() throws Exception {
        // Open a valid json(-ld) input file
        InputStream inputStream = classLoader.getResourceAsStream("sampleManyVocabs.jsonld");
        // Read the file into an Object (The type of this object will be a List,
        // Map, String, Boolean,
        // Number or null depending on the root object in the file).
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        // Create a context JSON map containing prefixes and definitions
        Map context = new HashMap();
        // Customise context...
        // Create an instance of JsonLdOptions with the standard JSON-LD options
        JsonLdOptions options = new JsonLdOptions();
        // Customise options...
        // Call whichever JSONLD function you want! (e.g. compact)
        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
        // Print out the result (or don't, it's your call!)
        System.out.println(JsonUtils.toPrettyString(compact));
    }

    @Test
    public void deveReconhecerPropriedadesEnderecoCompleto() throws Exception {
        InputStream inputStream = classLoader.getResourceAsStream("JohnPlainProperties.jsonld");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        System.out.println(JsonUtils.toPrettyString(jsonObject));

    }

    @Test
    public void deveReconhecerRepresentacoesComplexas() throws Exception {
        InputStream inputStream = classLoader.getResourceAsStream("JoaoPlainProperties.jsonld");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map<String, String> context = new HashMap<String, String>();
        JsonLdOptions options = new JsonLdOptions();
        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
        System.out.println(JsonUtils.toPrettyString(compact));

    }

    @Test
    public void showJsonLDwithContext() throws Exception {
        InputStream inputStream = classLoader.getResourceAsStream("JohnContextProperties.jsonld");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map<String, String> context = new HashMap<String, String>();
        JsonLdOptions options = new JsonLdOptions();
        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
        System.out.println(JsonUtils.toPrettyString(compact));
    }

}