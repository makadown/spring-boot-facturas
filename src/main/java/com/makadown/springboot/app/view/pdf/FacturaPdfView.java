package com.makadown.springboot.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.makadown.springboot.app.models.entity.Factura;

@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Factura factura = (Factura) model.get("factura");
		PdfPTable tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);
		tabla.addCell("Datos del cliente");
		tabla.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		tabla.addCell(factura.getCliente().getEmail());
		
		PdfPTable tabla2 = new PdfPTable(1);
		tabla2.addCell("Datos de la factura");
		tabla2.addCell("Folio: "+ factura.getId());
		tabla2.addCell("Descripción: " + factura.getDescripcion());
		tabla2.addCell("Fecha: " + factura.getCreateAt());
		tabla2.setSpacingAfter(20);
		
		document.add(tabla);
		document.add(tabla2);
	}

}
