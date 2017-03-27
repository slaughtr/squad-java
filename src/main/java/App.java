import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  Squad potatoSquad = new Squad("Potato Heads", "Keep All of their Parts");
  potatoSquad.save();
  Hero mrPotato = new Hero("Mr. Potato Head", "Spud Lee", "Interchangable body parts", "potato peelers", 4, 10, potatoSquad.getId());
  Hero mrsPotato = new Hero("Mrs. Potato Head", "TatterAnn Lee", "Interchangable body parts", "missing eyes", 3, 12, potatoSquad.getId());
  // potatoSquad.addHero(mrPotato);
  // potatoSquad.addHero(mrsPotato);
  Squad avengers = new Squad("The Avengers", "Protect human race from threats domestic and out of this world");
  Hero cap = new Hero("Captain America", "Steve Rogers", "Super American", "All of his friends are dead", 89, 55, avengers.getId());
  avengers.save();
  // avengers.addHero(cap);
  Hero hulk = new Hero("The Incredible Hulk", "David Banner", "Big and Green", "Uncontrolable", 99, 6, avengers.getId());
  // avengers.addHero(hulk);

  get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/squads/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/squad-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/squads", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String name = request.queryParams("squadName");
    String goal = request.queryParams("squadGoal");
    Squad newSquad = new Squad(name, goal);
    newSquad.save();
    model.put("template", "templates/squad-success.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/squads", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("squads", Squad.all());
    model.put("template", "templates/squads.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/squads/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
    model.put("squad", squad);
    model.put("template", "templates/squad.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/squads/:id/heroes/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
    model.put("squad", squad);
    model.put("template", "templates/squad-heroes-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/squads/:id/heroes", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
    String heroName = request.queryParams("heroName");
    String secretIdentity = request.queryParams("secretIdentity");
    String superPower = request.queryParams("superPower");
    String weakness = request.queryParams("weakness");
    int strengthValue = Integer.parseInt(request.queryParams("strengthValue"));
    int intelValue = Integer.parseInt(request.queryParams("intelValue"));
    Hero newHero = new Hero(heroName, secretIdentity, superPower, weakness, strengthValue, intelValue, squad.getId());
    newHero.save();
    model.put("squad", squad);
    model.put("template", "templates/squad-heroes-success.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/squads/:id1/heroes/:id2", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Squad squad = Squad.find(Integer.parseInt(request.params(":id1")));
    Hero hero = Hero.find(Integer.parseInt(request.params(":id2")));
    model.put("squad", squad);
    model.put("hero", hero);
    model.put("template", "templates/hero.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());
  }
}
