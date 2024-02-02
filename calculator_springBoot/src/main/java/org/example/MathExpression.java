package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/calc")
@CrossOrigin(value = "http://localhost:8081/")

public class MathExpression {
    @Autowired
    private MathExpressionService mathExpressionService;

    @PostMapping(value = "/math/expression")
    public void postExpression(@RequestBody ExpressionClass exp){
        mathExpressionService.setExpression(exp);
    }

    @GetMapping(value = "/final/result")
    public ExpressionClass getResult(){
        return new ExpressionClass(mathExpressionService.computeResult());
    }

    @GetMapping(value = "/multi/inverse")
    public ExpressionClass getMultiInverse(){
        return new ExpressionClass(mathExpressionService.multiInverse());
    }

    @GetMapping(value = "/power2")
    public ExpressionClass getPower2(){
        return new ExpressionClass(mathExpressionService.power2());
    }

    @GetMapping(value = "/square2")
    public ExpressionClass getSquare2(){
        return new ExpressionClass(mathExpressionService.square2());
    }

    @GetMapping(value = "/precent")
    public ExpressionClass getPrecent(){
        return new ExpressionClass(mathExpressionService.precent());
    }

    @GetMapping(value = "/change/sign")
    public ExpressionClass changeSign(){
        return new ExpressionClass(mathExpressionService.changeSign());
    }

}
