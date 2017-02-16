package org.blockzter.mqservice.model.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by mblock2 on 8/22/16.
 */
public class HexDeserializer implements JsonDeserializer<Long> {
	@Override
	public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Long.decode(json.getAsString());
	}
}
