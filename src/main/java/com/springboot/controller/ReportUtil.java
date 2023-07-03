package com.springboot.controller;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Retorna o PDF */
	public byte[] gerarRelatorio(List listDados, 
									String relatorio, 
									ServletContext servletContext) throws Exception {
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDados);
		/* Carrega o caminho */
		String caminhoJasper = servletContext.getRealPath("relatorios") + File.separator + relatorio + ".jasper";

		/* Carrega o arquivo */
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap<>(), jrbcds);

		return JasperExportManager.exportReportToPdf(impressoraJasper);
		
		
		
	}

}
