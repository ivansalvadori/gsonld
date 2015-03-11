package br.com.ivansalvadori.gsonld.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.com.ivansalvadori.gsonld.GsonLD;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class GsonLDSerializeTest {

    @Test
    public void fromSingleObjectToString() {
        JohnPlain johnPlain = new JohnPlain("Deer", "John", "2015-03-04");
        GsonLD gsonLD = new GsonLD();
        System.out.println(gsonLD.toJsonLD(johnPlain));
    }

    @Test
    public void fromComplexObjectToString() throws JsonLdError, JsonGenerationException, IOException {
        Endereco endereco = new Endereco("SC", "Florianópolis", "Rua João Pio Duarte Silva", "78850-000");
        Residencia residencia = new Residencia("Trindade - Florianópolis - Santa Catarina - Brazil", "55 48 1234 5678", endereco);
        JoaoPlain joao = new JoaoPlain("da Silva", "Joao", "2015-03-04", residencia, 12345);

        GsonLD gsonLD = new GsonLD();
        String jsonLD = gsonLD.toJsonLD(joao);
        System.out.println(jsonLD);

        Map<String, String> context = new HashMap<String, String>();
        JsonLdOptions options = new JsonLdOptions();
        Object fromString = JsonUtils.fromString(jsonLD);
        Object compact = JsonLdProcessor.compact(fromString, context, options);
        System.out.println(JsonUtils.toPrettyString(compact));
    }
}
