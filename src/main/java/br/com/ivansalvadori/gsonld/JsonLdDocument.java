package br.com.ivansalvadori.gsonld;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class JsonLdDocument {

    @SerializedName("@context")
    private final Map<String, String> context = new HashMap<String, String>();

    private final Map<String, Object> properties = new HashMap<String, Object>();

    public Map<String, Object> getProperties() {
        return this.properties;
    }

    public void addProperties(String propertyName, Object value) {
        this.properties.put(propertyName, value);
    }

    public Map<String, String> getContext() {
        return new HashMap<String, String>(this.context);
    }

    public void addContext(String contextName, String contextValue) {
        this.context.put(contextName, contextValue);
    }

    public void addContext(Map<String, String> context) {
        this.context.putAll(context);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        JsonObject jsonLd = new JsonObject();

        if (!this.context.isEmpty()) {
            JsonElement contextTree = gson.toJsonTree(this.context);
            jsonLd.add("@context", contextTree);
        }
        Set<String> keySet = this.properties.keySet();
        for (String propertyName : keySet) {
            JsonElement element = gson.toJsonTree(this.properties.get(propertyName));
            jsonLd.add(propertyName, element);
        }

        return gson.toJson(jsonLd);
    }

}
