package kz.alisher.springcourse.controllers.animals;


import kz.alisher.springcourse.dao.OthersDAO;
import kz.alisher.springcourse.dao.OthersRequestsDAO;
import kz.alisher.springcourse.models.OthersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalog/others/")
public class OthersAnimalsController {

    private final OthersDAO othersDAO;
    private final OthersRequestsDAO requestsDAO;

    @Autowired
    public OthersAnimalsController(OthersDAO othersDAO, OthersRequestsDAO requestsDAO) {
        this.othersDAO = othersDAO;
        this.requestsDAO = requestsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("pets", othersDAO.index());
        model.addAttribute("request", new OthersRequest());
        model.addAttribute("catValue", -1);
        return "catalog/pets/others";
    }

    @PostMapping("/{id}/book")
    public String booking(@ModelAttribute("request") OthersRequest othersRequest,
                          @PathVariable("id") int id){

        requestsDAO.bookNewOthers(othersRequest, id);
        return "redirect:/catalog/others/";

    }


}
