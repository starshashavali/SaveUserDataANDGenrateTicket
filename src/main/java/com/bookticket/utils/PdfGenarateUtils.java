package com.bookticket.utils;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import com.bookticket.domain.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PdfGenarateUtils {

	public byte[] generatePdf(User entity) throws Exception {
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);
		document.open();

		// Ticket Header
		Paragraph header = new Paragraph("Ticket Details");
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);

		// Create a table for the ticket details
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(80);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setPadding(10);

		// Add ticket details
		table.addCell("TicketId:");
		table.addCell(String.valueOf(entity.getId()));

		table.addCell("Name:");
		table.addCell(entity.getName());

		table.addCell("Phone Number:");
		table.addCell(entity.getPhno());

		table.addCell("Source Location:");
		table.addCell(entity.getSourceLocation());

		table.addCell("Destination:");
		table.addCell(entity.getDestination());

		table.addCell("Seat No:");
		table.addCell(String.valueOf(entity.getSeatNo()));

		table.addCell("Ticket Price:");
		table.addCell(String.valueOf(entity.getTicketPrice()));

		table.addCell("Date:");
		table.addCell(String.valueOf(entity.getCreatedAt()));

		document.add(table);
		document.close();

		return outputStream.toByteArray();
	}
}
