import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class App {

    public static void main(String[] args) { //type “psvm + tab” to autocreate this :)
        staticFileLocation("/public");

        //get: show new hero form
        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new hero form
        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String power = request.queryParams("power");
            String weakness= request.queryParams("weakness");
            Hero newHero = new Hero(name,age,power,weakness);
            model.put("post",newHero);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine() );


        //get: show an individual hero
        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Hero foundHero = Hero.findById(idOfHeroToFind); //use it to find post
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "hero-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show all heroes
        get("/",  (req, resp) -> {
            Map<String, Object> model =  new HashMap<String, Object>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes", heroes);
            return  new ModelAndView(model, "index.hbs");

        },new HandlebarsTemplateEngine());

        //get: show a form to update a hero
        get("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            String newAge = req.queryParams("age");
            String newPower = req.queryParams("power");
            String newWeakness = req.queryParams("weakness");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            model.put("editHero", editHero);
//            editPost.update(newContent); //don’t forget me
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a hero
        post("/heroes/:id/update", (req, res) -> { //URL to make new hero on hero route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            String newAge = req.queryParams("age");
            String newPower = req.queryParams("power");
            String newWeakness = req.queryParams("weakness");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            editHero.update(newName, newAge, newPower, newWeakness);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual hero
        get("/heroes/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Hero deleteHero = Hero.findById(idOfHeroToDelete); //use it to find post
            deleteHero.deleteHero();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
        get("/posts/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.clearAllHeroes();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        //====================================================================================================
        //get: show new hero form
        get("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new hero form
        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int maxSize= Integer.parseInt(request.queryParams("maxSize"));
            String cause = request.queryParams("cause");
            Squad newSquad = new Squad(name,maxSize,cause);
            model.put("post",newSquad);
            return new ModelAndView(model, "squadSuccess.hbs");
        }, new HandlebarsTemplateEngine() );


        //get: show an individual hero
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Squad foundSquad = Squad.findById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        //get: show all heroes
        get("/viewSquads",  (req, resp) -> {
            Map<String, Object> model =  new HashMap<String, Object>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads", squads);
            return  new ModelAndView(model, "squad.hbs");

        },new HandlebarsTemplateEngine());

        //get: show a form to update a hero
        get("/squads/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            String newMaxSize= req.queryParams("maxSize");
            String newCause = req.queryParams("cause");
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            model.put("editSquad", editSquad);
//            editPost.update(newContent); //don’t forget me
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a hero
        post("/squads/:id/update", (req, res) -> { //URL to make new hero on hero route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int newMaxSize = Integer.parseInt(req.queryParams("maxSize"));
            String newCause = req.queryParams("cause");
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            editSquad.update(newName, newMaxSize, newCause);
            return new ModelAndView(model, "squadSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual hero
        get("/squads/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
          Squad deleteSquad = Squad.findById(idOfSquadToDelete); //use it to find post
            deleteSquad.deleteSquad();
            return new ModelAndView(model, "squadSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
//        get("/squads/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            Squad.clearAllSquads();
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
    }

}
