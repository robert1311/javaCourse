/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbos.fieldagent.controller;

import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import corbos.fieldagent.service.LookupService;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Dev10
 */
@Controller
public class AgentController {

    @Autowired
    LookupService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("agents", service.findAllAgents());

        return "index";
    }

    @GetMapping("/addAgent")
    public String addAgentForm(Model model) {
        Agent agent = new Agent();
        List<Agency> agencies = service.findAllAgencies();
        List<SecurityClearance> clearances = service.findAllSecurityClearances();

        model.addAttribute("agent", agent);
        model.addAttribute("agencies", agencies);
        model.addAttribute("clearances", clearances);

        return "addAgent";
    }

    @PostMapping("/addAgent")
    public String addAgent(@Valid Agent agent, BindingResult result, HttpServletRequest request, Model model) {

        service.addAgentErrorValidation(agent, result, request);

        if (result.hasErrors()) {
            
            //String id = request.getParameter("agent");
            //agent = service.findAgentbyId(id).orElse(null);
            List<Agency> agencies = service.findAllAgencies();
            List<SecurityClearance> clearances = service.findAllSecurityClearances();

            model.addAttribute("agent", agent);
            model.addAttribute("agencies", agencies);
            model.addAttribute("clearances", clearances);

            return "/addAgent";
        } else {
            service.addAgent(agent);
        }
        return "redirect:/";
    }

    @GetMapping("/deleteAgent")
    public String deleteAgent(Model model, String id) {
        List<Assignment> assignments = service.getAssignmentsByAgentId(id);
        int assignmentCount = assignments.size();
        Agent agent = service.findAgentbyId(id).orElse(null);

        model.addAttribute("agent", agent);
        model.addAttribute("assignments", assignments);
        model.addAttribute("assignmentCount", assignmentCount);

        return "deleteAgent";
    }

    @GetMapping("/confirmDeleteAgent")
    public String confirmDeleteAgent(String id) {
        service.deleteAgent(id);
        return "redirect:/";
    }

    @GetMapping("/addAssignment")
    public String displayAddAssignment(String id, Model model) {
        Assignment assignment = new Assignment();

        Agent agent = service.findAgentbyId(id).orElse(null);
        List<Agent> agents = service.findAllAgents();
        List<Country> countries = service.findAllCountries();
        //Agent agent = service.findAgentbyId(id)

        model.addAttribute("assignment", assignment);
        model.addAttribute("viewedAgent", agent);
        model.addAttribute("agents", agents);
        model.addAttribute("countries", countries);

        return "addAssignment";
    }

    @PostMapping("/addAssignment")
    public String addAssignment(@Valid Assignment assignment, BindingResult result, HttpServletRequest request, Model model) {

        service.addAssignmentValidateDates(assignment, result, request);

        if (result.hasErrors()) {

            String id = request.getParameter("agent");
            Agent agent = service.findAgentbyId(id).orElse(null);
            List<Agent> agents = service.findAllAgents();
            List<Country> countries = service.findAllCountries();

            model.addAttribute("assignment", assignment);
            model.addAttribute("viewedAgent", agent);
            model.addAttribute("agents", agents);
            model.addAttribute("countries", countries);

            return "addAssignment";
        } else {
            service.addAssignment(assignment);
        }
        return "redirect:/";
    }

    @GetMapping("/editAgent")
    public String displayAgentInfo(Model model, String id) {

        Agent agent = service.findAgentbyId(id).orElse(null);
        List<Assignment> assignments = service.getAssignmentsByAgentId(id);
        List<Agency> agencies = service.findAllAgencies();
        List<SecurityClearance> clearances = service.findAllSecurityClearances();

        model.addAttribute("agent", agent);
        model.addAttribute("assignments", assignments);
        model.addAttribute("agencies", agencies);
        model.addAttribute("clearances", clearances);

        return "/editAgent";
    }

    @PostMapping("/editAgent")
    public String editAgent(@Valid Agent agent, BindingResult result, HttpServletRequest request, Model model) {
        
            service.editAgentErrorValidation(agent, result, request);

            if (result.hasErrors()) {

                String id = request.getParameter("identifier");
                List<Assignment> assignments = service.getAssignmentsByAgentId(id);
                List<Agency> agencies = service.findAllAgencies();
                List<SecurityClearance> clearances = service.findAllSecurityClearances();

                model.addAttribute("assignments", assignments);
                model.addAttribute("agent", agent);
                model.addAttribute("agencies", agencies);
                model.addAttribute("clearances", clearances);

                return "editAgent";
            
        } else {
            service.addAgent(agent);
        }
        return "redirect:/";
    }

    @GetMapping("/deleteAssignment")
    public String deleteAssignment(int id, String identifier) {

        //int assignmentId = Integer.parseInt(id);
        service.deleteAssignment(id);

        return "redirect:/editAgent?id=" + identifier + "";
    }

    @GetMapping("/viewAssignment")
    public String viewAssignment(int id, String identifier, Model model) {

        Assignment assignment = service.getAssignmentById(id);
        Agent agent = service.findAgentbyId(identifier).orElse(null);
        List<Agent> agents = service.findAllAgents();
        List<Country> countries = service.findAllCountries();

        model.addAttribute("assignment", assignment);
        model.addAttribute("viewedAgent", agent);
        model.addAttribute("agents", agents);
        model.addAttribute("countries", countries);

        return "viewAssignment";
    }

    @PostMapping("editAssignment")
    public String editAssignment(@Valid Assignment assignment, BindingResult result, HttpServletRequest request, Model model) {

        service.addAssignmentValidateDates(assignment, result, request);

        if (result.hasErrors()) {
      
            String id = request.getParameter("agent");
            Agent agent = service.findAgentbyId(id).orElse(null);
            List<Agent> agents = service.findAllAgents();
            List<Country> countries = service.findAllCountries();

            model.addAttribute("assignment", assignment);
            model.addAttribute("viewedAgent", agent);
            model.addAttribute("agents", agents);
            model.addAttribute("countries", countries);
            return "viewAssignment";
        }
        service.addAssignment(assignment);
        return "redirect:/";
    }
}
