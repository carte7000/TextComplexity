package org.ift7022.tp3.services;

import javax.ws.rs.core.Response;

import org.ift7022.tp3.texts.Text;
import org.ift7022.tp3.texts.TextCache;

public class Service implements ServiceHandler {

	private TextCache textCache;
	private Text text;

	public Response analyzeComplexityText(ComplexityTextDTO complexityTextDTO) {
		textCache.persist(complexityTextDTO);
		text.updateText(complexityTextDTO.content);
		int maxRank = text.getMaxRank();

		if (maxRank == 5000) {
			ComplexityResult complexityResult = new ComplexityResult();
			complexityResult.result = "text très compliqué";
			return Response.ok().entity(complexityResult).build();

		}

	}

}
