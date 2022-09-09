package com.example.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.Farmer;
import com.example.service.FarmerService;;



@RestController
public class FarmerController {
	@Autowired
FarmerService  farmerservice;
	
	
//	@GetMapping("/")
//	public ModelAndView index() {
//		ModelAndView mod=new ModelAndView("index");
//		return mod;
//	}
////	@RequestMapping(value="/search",method ={RequestMethod.GET,RequestMethod.POST} )
//	public String searchAllByName(Model model,@RequestParam String name) {
//		model.addAttribute("farmers", farmerservice.findAllByName(name));
//		System.out.println(farmerservice.findAllByName(name));
//		return "all-farmers.html";
//	}

//	
//	List<Farmer> farmer= new ArrayList<>();
//
//	@PostMapping("/saveFarmer")
//	public ResponseEntity<Object> addBook(@RequestBody Farmer farm) {
//		farmer.add(farm);
//		ServiceResponse<Farmer> response = new ServiceResponse<Farmer>("success", farm);
//		return new ResponseEntity<Object>(response, HttpStatus.OK);
//	}
@GetMapping("/farmers/list")
@ResponseBody
private List<Farmer> allFarmers(){      
    return farmerservice.findallFarmers();
}  	
	
//	@GetMapping("/addnewfarmers")
//	public RedirectView addFarmer(@Valid Farmer farmer) {
//		 Farmer resultFarmer = farmerservice.addFarmer(farmer);
//		 System.out.format("Added farmer with id = %s", resultFarmer.getFarmerId());
//		RedirectView redirectView = new RedirectView();
//		redirectView.setUrl("http://localhost:8080/add-farmer.html");
//        return redirectView;
//	}
//	
@PostMapping("/addfarmers")  
private int saveStudent(@RequestBody Farmer farmer)   
{  
farmerservice.addFarmer(farmer);  
return farmer.getFarmerId();  
}  
	
	
	@GetMapping("/search")
	public ModelAndView viewAddFarmer() {
		ModelAndView mod=new ModelAndView("all-farmers");
		return mod;
	}
	
//	@GetMapping("/add")
//	public ModelAndView ddFarmer() {
//		ModelAndView mod=new ModelAndView("page2");
//		return mod;
//	}
//	
	@ResponseBody
	@GetMapping("/hello")
public String nam() {
		return "hello  world";
	}
	
	@ResponseBody
	@GetMapping( value={"/farmers/addnewfarmer"} )
    public String addFarmer(@Valid Farmer farmer,BindingResult br)
    {   
        System.out.println("form data: "+farmer.getName());
        // Save the farmer in the DB
        if(br.hasErrors()) {
        	 return "{\"message\":\"new farmer creation failed due to validation errors\"}";
        }
       farmerservice.addFarmer(farmer);
       // RedirectView redirectView = new RedirectView("/farmers/addnewfarmer"); 
        return "{\"message\":\"farmer added successfully\"}";
    }
	
	 
//	@GetMapping("/helo")
//	@ResponseBody
//	public String viewo() {
//		return "ley"; 
//	}
//	
}
