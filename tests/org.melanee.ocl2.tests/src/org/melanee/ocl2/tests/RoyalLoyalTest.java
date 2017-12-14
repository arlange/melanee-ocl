/*******************************************************************************
 * Copyright (c) 2012, 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralph Gerbig - initial API and implementation and initial documentation
 *    Arne Lange - ocl2 implementation
 *******************************************************************************/
package org.melanee.ocl2.tests;

import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserInterpreter;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclLexer;
import org.melanee.ocl2.grammar.definition.grammar.DeepOclParser;

public class RoyalLoyalTest {

	/**
	 * Invariants
	 * 
	 * @throws IOException
	 */
	@Test
	public void testInv() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_inv.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		ParserInterpreter pars = new ParserInterpreter(parser.getGrammarFileName(), parser.getVocabulary(),
				Arrays.asList(parser.getRuleNames()), parser.getATNWithBypassAlts(), parser.getTokenStream());
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		System.out.println("interpreter: " + pars.getParseInfo());
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testInv1() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_inv1.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testInv2() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_inv2.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testInv3() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_inv3.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testInv4() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_inv4.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testInv5() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_inv5.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testInv6() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_inv6.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	/**
	 * Def
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDef() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_def.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testDef1() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_def1.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testDef2() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_def2.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	/**
	 * Derive
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDerive() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_derive.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	/**
	 * Init
	 * 
	 * @throws IOException
	 */
	@Test
	public void testInit() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_init.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testInit1() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_init1.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	/**
	 * Body
	 * 
	 * @throws IOException
	 */
	@Test
	public void testBody() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_body.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testBody1() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_body1.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	/**
	 * Let
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLet() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_let.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	/**
	 * Post
	 * 
	 * @throws IOException
	 */
	@Test
	public void testPost() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_post.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testPost1() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_post1.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testPost2() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_post2.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}

	@Test
	public void testPrePost() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_prePost.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}
	
	@Test
	public void nestedSelectTest() throws IOException {
		DeepOclLexer oclLexer = new DeepOclLexer(
				new ANTLRInputStream(getClass().getResourceAsStream("resources/rl_nestedSelect.ocl")));
		DeepOclParser parser = new DeepOclParser(new CommonTokenStream(oclLexer));
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
			}
		});
		ParseTree tree = parser.contextDeclCS();
		System.out.println(tree.toStringTree(parser));
	}
}
