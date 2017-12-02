package pl.poznan.put.student.spacjalive.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.student.spacjalive.erp.entity.Position;
import pl.poznan.put.student.spacjalive.erp.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/list")
    public String listPositions(Model model) {

        List<Position> positions = positionService.getPositions();

        model.addAttribute("positions", positions);

        return "list-positions";
    }

    @GetMapping("/addPositionForm")
    public String addPositionForm(Model model) {

        Position position = new Position();

        model.addAttribute("position", position);

        return "add-position-form";
    }

    @GetMapping("/deletePosition")
    public String deletePosition(@RequestParam("positionId") int positionId) {

        positionService.deletePosition(positionId);

        return "redirect:/position/list";
    }

    @GetMapping("updatePositionForm")
    public String updatePositionForm(@RequestParam("positionId") int positionId, Model model) {

        Position position = positionService.getPosition(positionId);

        model.addAttribute("position", position);

        return "add-position-form";
    }

    @PostMapping("/addPosition")
    public String addPosition(@ModelAttribute("position") @Valid Position position, BindingResult result) {

        if(result.hasErrors()) {
            return "add-position-form";
        }

        positionService.savePosition(position);

        return "redirect:/position/list";
    }

}
