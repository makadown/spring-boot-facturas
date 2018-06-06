package com.makadown.springboot.app.view.xls;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> arg0, Workbook arg1, HttpServletRequest arg2,
			HttpServletResponse arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
