package br.com.srs.gsonld.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.com.srs.gsonld.GsonLD;

import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class GsonLDTest {

    @Test
    public void fromStringPlainPropertiesToObject() throws Exception {
        String sampleFileAsString = getSampleFileAsString("JohnPlainProperties.jsonld");
        GsonLD gsonLD = new GsonLD();
        JohnPlain john = gsonLD.fromJsonLD(sampleFileAsString, JohnPlain.class);

        JohnPlain johnPlainExpected = new JohnPlain("Deer", "John", "2015-03-04");
        org.junit.Assert.assertEquals(johnPlainExpected, john);
    }

    @Test
    public void fromStringPlainPropertiesComplexToObject() throws Exception {
        String sampleFileAsString = getSampleFileAsString("JoaoPlainProperties.jsonld");
        JoaoPlain joao = new GsonLD().fromJsonLD(sampleFileAsString, JoaoPlain.class);

        Endereco enderecoExpected = new Endereco("SC", "Florianópolis", "Rua João Pio Duarte Silva", "78850-000");
        Residencia residenciaExpected = new Residencia("Trindade - Florianópolis - Santa Catarina - Brazil", "55 48 1234 5678", enderecoExpected);
        JoaoPlain joaoExpected = new JoaoPlain("da Silva", "Joao", "2015-03-04", residenciaExpected, 12345);

        org.junit.Assert.assertEquals(joaoExpected, joao);

    }

    @Test
    public void fromStringContextPropertiesComplexToObject() throws Exception {
        String sampleFileAsString = getSampleFileAsString("JohnContextProperties.jsonld");
        GsonLD gsonLD = new GsonLD();
        JohnPlain john = gsonLD.fromJsonLD(sampleFileAsString, JohnPlain.class);

        JohnPlain johnPlainExpected = new JohnPlain("Deer", "John", "2015-03-04");
        org.junit.Assert.assertEquals(johnPlainExpected, john);
    }

    private String getSampleFileAsString(String filename) throws IOException, JsonLdError {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map<String, String> context = new HashMap<String, String>();
        JsonLdOptions options = new JsonLdOptions();
        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
        String jsonLD = JsonUtils.toPrettyString(jsonObject);
        return jsonLD;
    }

}
