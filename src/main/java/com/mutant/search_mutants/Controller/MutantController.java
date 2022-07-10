package com.mutant.search_mutants.Controller;

import com.mutant.search_mutants.Common.MutantBody;
import com.mutant.search_mutants.Common.Stat;
import com.mutant.search_mutants.Service.IMutantService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MutantController {

    @Autowired
    IMutantService mutantService;

    @GetMapping("stats")
    public ResponseEntity<?> data() {

        Stat stat = mutantService.getStats();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(stat);
    }

    @PostMapping("mutant")
    public ResponseEntity<?> create(@RequestBody @NotNull MutantBody mutantBody) {
        Boolean response = mutantService.ValidateMutant(mutantBody.getDna());
        if (response)
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

}
