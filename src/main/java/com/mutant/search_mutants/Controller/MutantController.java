package com.mutant.search_mutants.Controller;

import com.mutant.search_mutants.Common.MutantBody;
import com.mutant.search_mutants.Service.IMutantService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MutantController {

    @Autowired
    IMutantService mutantService;

    @GetMapping("mutant")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("mutant")
    public boolean create(@RequestBody @NotNull MutantBody mutantBody) {

        return  mutantService.ValidateMutant(mutantBody.getDna());
    }

}
