package br.inf.furb.tcc.robotoy.control;

import java.io.File;
import java.io.IOException;

public interface ICompilerFacade {

    void save(File file, String content) throws IOException;

    String open(File file) throws IOException;

}
