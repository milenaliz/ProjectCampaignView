package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
public class CampaignController {
    @GetMapping("/campaigns")
    public ModelAndView registerPage(){
        return new ModelAndView("register_campaign")
                .addObject("campaign", new CampaignDto());
    }

    @PostMapping("/campaigns")
    public ModelAndView register(@ModelAttribute CampaignDto campaignDto, Principal principal) {
        RestTemplate template = new RestTemplate();
        String loggedUserEmail = principal.getName();

        ResponseEntity<CustomerDto> loggedCustomerResponse = template.getForEntity(
                "http://localhost:8080/customers/customers/" + loggedUserEmail, CustomerDto.class);
        CustomerDto loggedCustomer = loggedCustomerResponse.getBody();
        campaignDto.setCustomerId(loggedCustomer.getId());
        campaignDto.setBrand(loggedCustomer.getBrand());
        ResponseEntity<CampaignDto> response =
                template.postForEntity("http://localhost:8080/campaigns",
                        campaignDto, CampaignDto.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return new ModelAndView("reg_camp_success");
        } else {
            return new ModelAndView("register_campaign")
                    .addObject("campaign", campaignDto);
        }
    }

        @GetMapping("/campaigns/all")
        public ModelAndView allCampaigns(){
            RestTemplate template = new RestTemplate();
            ResponseEntity<List> campaigns = template.getForEntity("http://localhost:8080/campaigns/all", List.class);

            ModelAndView modelAndView = new ModelAndView("campaigns_all");
            modelAndView.addObject("campaigns",campaigns.getBody());
            return modelAndView;
    }
}
