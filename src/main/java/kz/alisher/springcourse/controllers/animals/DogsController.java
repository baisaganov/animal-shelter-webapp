package kz.alisher.springcourse.controllers.animals;

import kz.alisher.springcourse.dao.DogDAO;
import kz.alisher.springcourse.dao.RequestsDAO;
import kz.alisher.springcourse.models.DogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalog/dogs/")
public class DogsController {

    private final DogDAO dogDAO;
    private final RequestsDAO requestsDAO;

    @Autowired
    public DogsController(DogDAO dogDAO, RequestsDAO requestsDAO) {
        this.dogDAO = dogDAO;
        this.requestsDAO = requestsDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("pets", dogDAO.index());
        model.addAttribute("request", new DogRequest());
        model.addAttribute("dogValue", -1);
        System.out.println(dogDAO.index());
        return "catalog/pets/dogs";
    }

    @PostMapping("/{id}/book")
    public String booking(@ModelAttribute("request") DogRequest dogRequest,
                          @PathVariable("id") int id){
        System.out.println(dogRequest.getName());
        System.out.println(id);

        requestsDAO.bookNewDog(dogRequest, id);
        return "redirect:/catalog/dogs/";

    }


}
