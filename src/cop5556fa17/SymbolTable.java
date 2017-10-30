package cop5556fa17;

import java.util.HashMap;

import cop5556fa17.AST.ASTNode;

public class SymbolTable {
    HashMap<String, ASTNode> hm;
    SymbolTable(){
        hm = new HashMap<String, ASTNode>();
    }
    void insert(String key,ASTNode node){
        hm.put(key, node);
    }
    boolean iscontains(String key){
        return hm.containsKey(key);
    }
    ASTNode getfromSymboltable(String key){
        return hm.get(key);
    }
    

}
