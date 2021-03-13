package org.usky.ant.ip;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

import static org.usky.ant.ip.IPUtil.randIpStr;

public class IPFileGenerator {
	public void generate(String path, int size) throws IOException {
		try (FileOutputStream osm = new FileOutputStream(path);
			 OutputStreamWriter writer = new OutputStreamWriter(osm);
			 BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

			Random r = new Random();
			while (size > 0){
				String ipStr = randIpStr(r);
				size -= ipStr.getBytes().length;
				bufferedWriter.write(ipStr + "\n");
			}
			bufferedWriter.flush();
		}
	}


}
