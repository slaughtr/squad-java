import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

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
    Hero newHero = new Hero(heroName, secretIdentity, superPower, weakness, strengthValue, intelValue);
    squad.addHero(newHero);
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
