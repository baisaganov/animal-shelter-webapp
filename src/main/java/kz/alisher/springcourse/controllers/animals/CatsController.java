package kz.alisher.springcourse.controllers.animals;

import kz.alisher.springcourse.dao.CatDAO;
import kz.alisher.springcourse.dao.CatRequestsDAO;
import kz.alisher.springcourse.models.CatRequest;
import kz.alisher.springcourse.models.DogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalog/cats/")
public class CatsController {

    private final CatDAO catDAO;
    private final CatRequestsDAO requestsDAO;

    @Autowired
    public CatsController(CatDAO catDAO, CatRequestsDAO requestsDAO) {
        this.catDAO = catDAO;
        this.requestsDAO = requestsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("pets", catDAO.index());
        model.addAttribute("request", new DogRequest());
        model.addAttribute("catValue", -1);
        System.out.println(catDAO.index());
        return "catalog/pets/cats";
    }

    @PostMapping("/{id}/book")
    public String booking(@ModelAttribute("request") CatRequest catRequest,
                          @PathVariable("id") int id){
        System.out.println(catRequest.getName());
        System.out.println(id);

        requestsDAO.bookNewCat(catRequest, id);
        return "redirect:/catalog/cats/";

    }


}
