package org.usky.ant.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class IPReader {
	InputStream ism;
	public IPReader(InputStream ism){
		this.ism = ism;
	}
	public void foreach(Consumer<String> ipConsumer) throws IOException {

		try(InputStreamReader reader = new InputStreamReader(ism);
			BufferedReader bufferedReader = new BufferedReader(reader)){
			String line;
			while ((line = bufferedReader.readLine()) != null){
				ipConsumer.accept(line);
			}
		}
	}
}
