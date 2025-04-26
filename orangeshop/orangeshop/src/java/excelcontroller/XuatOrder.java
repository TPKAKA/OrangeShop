/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package excelcontroller;

import dal.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import model.Orders;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
@WebServlet(name = "XuatOrder", urlPatterns = {"/xuatorder"})
public class XuatOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        List<Orders> orderss = orderDAO.getOrder();

        int maximum = 56489654;
        int minimum = 1;
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;
        String filePath = "C:\\ExcelWebBanHang\\" + "Order" + Integer.toString(randomNum) + ".xlsx";
        FileOutputStream file = new FileOutputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("0");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;
        XSSFCell cell4;
        XSSFCell cell5;

        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("OrderID");
        cell1 = row.createCell(1);
        cell1.setCellValue("Customer Name");
        cell2 = row.createCell(2);
        cell2.setCellValue("Order Date");
        cell3 = row.createCell(3);
        cell3.setCellValue("Total amount");
        cell4 = row.createCell(4);
        cell4.setCellValue("Status Payment");
        cell5 = row.createCell(5);
        cell5.setCellValue("Status Order");

        int i = 0;

        for (Orders pro : orderss) {
            i = i + 1;
            row = workSheet.createRow(i);
            cell0 = row.createCell(0);
            cell0.setCellValue(pro.getOrderid());
            cell1 = row.createCell(1);
            cell1.setCellValue(pro.user.getFullName());
            cell2 = row.createCell(2);
            cell2.setCellValue(pro.getDate());
            cell3 = row.createCell(3);
            cell3.setCellValue(pro.getTotalamount());
            cell4 = row.createCell(4);
            cell4.setCellValue(pro.getStatuspayment());
            cell5 = row.createCell(5);
            cell5.setCellValue(pro.getStatusorder());
        }

        workbook.write(file);
        workbook.close();
        file.close();

        request.setAttribute("mess", "Đã xuất file Excel thành công!");
        request.getRequestDispatcher("ordermanager").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
