package com.bloodpool.controller;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bloodpool.entity.UserDetailsEntity;
import com.bloodpool.service.PatientDetailsService;
import com.bloodpool.service.SendRequestService;

//import com.bloodpool.service.UserDetailsService;

@SuppressWarnings("serial")
public class SendRequestController extends HttpServlet  {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, NullPointerException, ServletException, NotSerializableException {

		PatientDetailsService patientDetailService = new PatientDetailsService();
		SendRequestService sendRequestService = new SendRequestService();

		String patientFirstName = req.getParameter("pfname");
		String patientLastName = req.getParameter("plname");
		String hospitalAddressOne = req.getParameter("addressOne");
		String hospitalAddressTwo = req.getParameter("addressTwo");
		int optionOne = Integer.parseInt(req.getParameter("optionOne"));
		int bloodTypeOne = Integer.parseInt(req.getParameter("bloodTypeOne"));
		int reqUnitOne = Integer.parseInt(req.getParameter("reqUnitOne"));
		int optionTwo = Integer.parseInt(req.getParameter("optionTwo"));
		int bloodTypeTwo = Integer.parseInt(req.getParameter("bloodTypeTwo"));
		int reqUnitTwo = Integer.parseInt(req.getParameter("reqUnitTwo"));

		System.out.println(patientFirstName + " " + patientLastName);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date requireDate = new Date();
		try {
			requireDate = simpleDateFormat.parse(req.getParameter("requireDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String patientID = patientDetailService.registerPatient(patientFirstName, patientLastName, hospitalAddressOne,
				hospitalAddressTwo, requireDate, optionOne, bloodTypeOne, reqUnitOne, optionTwo, bloodTypeTwo,
				reqUnitTwo);

		ArrayList<UserDetailsEntity> donorList = sendRequestService.sendBloodRequests();
		ArrayList<UserDetailsEntity> bloodDonorList = donorList;
		ArrayList<UserDetailsEntity> plateletDonorList = sendRequestService.sendPlateletRequest(donorList, patientID);
		for(int i=0;i<bloodDonorList.size();i++)
			   System.out.print(bloodDonorList.get(i).getFirstName());
		HttpSession sess = req.getSession(false);
		if(((bloodDonorList!=null)&&(donorList.size()!=0))||((plateletDonorList!=null)&&(plateletDonorList.size()==0))){
		sess.setAttribute("patientID", patientID);
		sess.setAttribute("bloodDonorList", bloodDonorList);
		sess.setAttribute("plateletDonorList", plateletDonorList);
		resp.sendRedirect("DistMat.jsp");
		}
	}
}