package com.iesvdc.dam.lmsgi;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

/**
 * Hello world!
 *
 */
public class Xslt 
{
	public static void main( String[] args )
	{
		if(args.length == 3) {

			try {
				Processor proc = new Processor(false);
				XsltCompiler comp = proc.newXsltCompiler();
				XsltExecutable exp;
				exp = comp.compile(new StreamSource(new File(args[1])));
				XdmNode source = proc.newDocumentBuilder().build(new StreamSource(new File(args[0])));
				Serializer out = proc.newSerializer();
				out.setOutputProperty(Serializer.Property.METHOD, "html");
				out.setOutputProperty(Serializer.Property.INDENT, "yes");
				out.setOutputFile(new File(args[2]));
				XsltTransformer trans = exp.load();
				trans.setInitialContextNode(source);
				trans.setDestination(out);
				trans.transform();

				System.out.println("Output written to books.html");
			} catch (SaxonApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
