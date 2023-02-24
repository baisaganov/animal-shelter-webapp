package kz.alisher.springcourse.controllers.adminpanel;

import kz.alisher.springcourse.dao.*;
import kz.alisher.springcourse.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/login")
public class LoginController {

    private final AdminDAO adminDAO;
    private final DogDAO dogDAO;
    private final CatDAO catDAO;
    private final OthersDAO othersDAO;
    private final RequestsDAO requestsDAO;
    private final CatRequestsDAO catRequestsDAO;
    private final OthersRequestsDAO othersRequestsDAO;
    private Admin administation;


    @Autowired
    public LoginController(AdminDAO adminDAO, DogDAO dogDAO, CatDAO catDAO, OthersDAO othersDAO, RequestsDAO requestsDAO, CatRequestsDAO catRequestsDAO, OthersRequestsDAO othersRequestsDAO) {
        this.adminDAO = adminDAO;
        this.dogDAO = dogDAO;
        this.catDAO = catDAO;
        this.othersDAO = othersDAO;
        this.requestsDAO = requestsDAO;
        this.catRequestsDAO = catRequestsDAO;
        this.othersRequestsDAO = othersRequestsDAO;
    }


    @GetMapping()
    public String login(Model model){
        model.addAttribute("admin", new Admin());
        return "adminpanel/admin";
    }

    @PostMapping()
    public String checkAdmin(@ModelAttribute("admin") Admin admin, Model model) throws CloneNotSupportedException {
        if(adminDAO.auth(admin.getName(), admin.getPassword())) {
            this.administation = (Admin) admin.clone();
            return "redirect:/login/home?username=" + admin.getName() + "&password=" + admin.getPassword();
        }

        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage(@RequestParam String username, @RequestParam String password, Model model){
        if(adminDAO.auth(username, password)) {
            Admin admin = new Admin(0,username,password);
            model.addAttribute("admin", admin);
            model.addAttribute("pets", dogDAO.index());
            model.addAttribute("cats", catDAO.index());
            model.addAttribute("others", othersDAO.index());
            return "adminpanel/home";
        }

        return "redirect:/login";
    }

    /** ___________________________ DOG CAT OTHERS EDIT ________________________________*/

    @GetMapping("/{id}/dog_edit")
    public String petEdit(@RequestParam String username, @RequestParam String password, Model model, @PathVariable("id") int id){
        Admin admin = new Admin(0,username,password);
        model.addAttribute("admin", admin);
        model.addAttribute("dog", dogDAO.getDog(id));
        return "adminpanel/dog_edit";
    }

    @GetMapping("/{id}/cat_edit")
    public String catEdit(@RequestParam String username, @RequestParam String password, Model model, @PathVariable("id") int id){
        Admin admin = new Admin(0,username,password);
        model.addAttribute("admin", admin);
        model.addAttribute("cat", catDAO.getCat(id));
        return "adminpanel/cat_edit";
    }

    @GetMapping("/home/others/{id}/edit")
    public String othersEdit(@RequestParam String username, @RequestParam String password, Model model, @PathVariable("id") int id){
        Admin admin = new Admin(0,username,password);
        model.addAttribute("admin", admin);
        model.addAttribute("other", othersDAO.getOthers(id));
        return "adminpanel/other_edit";
    }

    /** ________________________________CATALOG EDIT________________________________*/

    @PostMapping ("/home/{id}/edit")
    public String dogEditPatching(@RequestParam String username,
                                  @RequestParam String password,
                                  @ModelAttribute("dog") Dog dog,
                                  @PathVariable("id") int id,
                                  Model model){
        dogDAO.update(id, dog);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }

    @PostMapping ("/home/cat/{id}/edit")
    public String catEditPatching(@RequestParam String username,
                                  @RequestParam String password,
                                  @ModelAttribute("cat") Cat cat,
                                  @PathVariable("id") int id,
                                  Model model){
        catDAO.update(id, cat);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }

    @PostMapping ("/home/others/{id}/edit")
    public String othersEditPatching(@RequestParam String username,
                                  @RequestParam String password,
                                  @ModelAttribute("others") Others others,
                                  @PathVariable("id") int id,
                                  Model model){
        othersDAO.update(id, others);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }

    /** ________________________________CATALOG DELETE________________________________*/
    @PostMapping("/home/{id}/delete")
    public String dogDelete(@RequestParam String username,
                            @RequestParam String password,
                            @ModelAttribute("dog") Dog dog,
                            @PathVariable("id") int id,
                            Model model){
        System.out.println("DELETE id " + id);
        dogDAO.delete(id);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }

    @PostMapping("/home/cat/{id}/delete")
    public String catDelete(@RequestParam String username,
                            @RequestParam String password,
                            @ModelAttribute("cat") Cat cat,
                            @PathVariable("id") int id,
                            Model model){
        System.out.println("DELETE id " + id);
        catDAO.delete(id);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }

    @PostMapping("/home/other/{id}/delete")
    public String othersDelete(@RequestParam String username,
                            @RequestParam String password,
                            @ModelAttribute("others") Others others,
                            @PathVariable("id") int id,
                            Model model){
        othersDAO.delete(id);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }

    /**________________________________ ADD NEW ________________________________ */
    @GetMapping("/home/new")
    public String create(@RequestParam String username,
                         @RequestParam String password,
                         @ModelAttribute("dog") Dog dog,
                         Model model){
        model.addAttribute("dog", new Dog());
        model.addAttribute("admin", new Admin(0, username, password));
        return "adminpanel/new";
    }
    @PostMapping("/home/new")
    public String createNew(@RequestParam String username,
                            @RequestParam String password,
                            @ModelAttribute("dog") Dog dog){
        dogDAO.addNew(dog);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }

    // CAT Create POST/GET
    @GetMapping("/home/cat/new")
    public String createCat(@RequestParam String username,
                         @RequestParam String password,
                         @ModelAttribute("cat") Cat cat,
                         Model model){
        model.addAttribute("cat", new Cat());
        model.addAttribute("admin", new Admin(0, username, password));
        return "adminpanel/new_cat";
    }
    @PostMapping("/home/cat/new")
    public String createNewCat(@RequestParam String username,
                            @RequestParam String password,
                            @ModelAttribute("cat") Cat cat){
        System.out.println(cat.getName());
        catDAO.addNew(cat);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }



    @GetMapping("/home/other/new")
    public String createOthers(@RequestParam String username,
                            @RequestParam String password,
                            @ModelAttribute("others") Others others,
                            Model model){
        model.addAttribute("other", new Others());
        model.addAttribute("admin", new Admin(0, username, password));
        return "adminpanel/new_other";
    }

    @PostMapping("/home/other/new")
    public String createNewOthers(@RequestParam String username,
                               @RequestParam String password,
                               @ModelAttribute("other") Others others){
        othersDAO.addNew(others);
        return "redirect:/login/home?username=" + username + "&password=" + password;
    }


    /**________________________________REQUESTS________________________________*/
    @GetMapping("/requests")
    public String showRequests(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        model.addAttribute("dogRequests", requestsDAO.getAll());
        model.addAttribute("admin", new Admin(0, username, password));
        model.addAttribute("requestDone", new DogRequest());
        model.addAttribute("catRequestDone", new CatRequest());
        model.addAttribute("othersRequestDone", new OthersRequest());
        model.addAttribute("catRequests", catRequestsDAO.getAll());
        model.addAttribute("othersRequests", othersRequestsDAO.getAll());
        return "adminpanel/requests";
    }

    /** ________________________________ REQUESTS CANCEL ________________________________*/
    @PostMapping("/requests/cat/{id}/cancel")
    public String catRequestDone(@RequestParam String username,
                                 @RequestParam String password,
                                 @ModelAttribute("catRequestDone") CatRequest catRequest,
                                 @PathVariable("id") int id){
        catRequestsDAO.catRequestDelete(id);
        return "redirect:/login/requests?username=" + username + "&password=" + password;
    }

    @PostMapping("/requests/others/{id}/cancel")
    public String othersRequestDone(@RequestParam String username,
                                 @RequestParam String password,
                                 @ModelAttribute("othersRequestDone") OthersRequest OthersRequest,
                                 @PathVariable("id") int id){
        othersRequestsDAO.othersRequestDelete(id);
        return "redirect:/login/requests?username=" + username + "&password=" + password;
    }

    @PostMapping("/requests/{id}/done")
    public String requestDone(@RequestParam String username,
                              @RequestParam String password,
                              @ModelAttribute("requestDone") DogRequest dogRequest,
                              @PathVariable("id") int id){
        requestsDAO.requestDeleteDog(id);
        return "redirect:/login/requests?username=" + username + "&password=" + password;
    }

    /** ________________________________ REQUEST DONE ________________________________*/

    @PostMapping("/requests/{id}/delete")
    public String requestDelete(@RequestParam String username,
                                @RequestParam String password,
                                @ModelAttribute("requestDone") DogRequest dogRequest,
                                @PathVariable("id") int id){
        DogRequest dogRequest1 = requestsDAO.index(id);
        requestsDAO.requestDeleteDog(id);
        dogDAO.delete(dogRequest1.getDog().getId());
        return "redirect:/login/requests?username=" + username + "&password=" + password;
    }

    @PostMapping("/requests/cat/{id}/delete")
    public String catRequestDelete(@RequestParam String username,
                                @RequestParam String password,
                                @ModelAttribute("requestDone") CatRequest catRequest,
                                @PathVariable("id") int id){
        CatRequest catRequest1 = catRequestsDAO.index(id);
        catRequestsDAO.catRequestDelete(id);
        catDAO.delete(catRequest1.getCat().getId());
        return "redirect:/login/requests?username=" + username + "&password=" + password;
    }

    @PostMapping("/requests/others/{id}/delete")
    public String othersRequestDelete(@RequestParam String username,
                                @RequestParam String password,
                                @ModelAttribute("requestDone") OthersRequest othersRequest,
                                @PathVariable("id") int id){
            OthersRequest catRequest1 = othersRequestsDAO.index(id);
        othersRequestsDAO.othersRequestDelete(id);
        othersDAO.delete(catRequest1.getOthers().getId());
        return "redirect:/login/requests?username=" + username + "&password=" + password;
    }


}
