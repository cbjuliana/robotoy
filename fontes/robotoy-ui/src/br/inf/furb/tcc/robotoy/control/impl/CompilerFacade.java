package br.inf.furb.tcc.robotoy.control.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.IOUtils;

import br.inf.furb.tcc.robotoy.control.ICompilerFacade;

public class CompilerFacade implements ICompilerFacade {

	@Override
	public void save(File file, String content) throws IOException {
		try (PrintStream stream = new PrintStream(file)) {
			stream.print(content);
		}
	}

	@Override
	public String open(File file) throws IOException {
		return IOUtils.toString(new FileInputStream(file));
	}

}
