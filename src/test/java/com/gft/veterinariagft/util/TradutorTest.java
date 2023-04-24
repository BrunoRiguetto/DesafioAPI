package com.gft.veterinariagft.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class TradutorTest {

	@Test
	void testTranslateNaoDeveRetornarError() throws IOException {
		String ingleString = "I really liked java";
		assertDoesNotThrow( () ->Tradutor.translate("en", "pt", ingleString) );
		
	}
	
	@Test
	void testTranslateDeveRetornarError() {
		
		assertThrows(NullPointerException.class,
				() ->Tradutor.translate("en", "pt", null));

	} 
	
	@Test
	void testTranslate() throws IOException {
		String ingleString = "I really liked java";
		String string = Tradutor.translate("en", "pt", ingleString);
		
		assertEquals(string, "gostei muito do java");
	}

}
