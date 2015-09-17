/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.company.model.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tan.jam.company.model.entity.KeywordsDynamic;

/**
 *
 * @author Lenovo
 */
@Stateless
public class KeywordsDynamicFacade extends AbstractFacade<KeywordsDynamic> {
    @PersistenceContext(unitName = "WebApplication4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KeywordsDynamicFacade() {
        super(KeywordsDynamic.class);
    }
    
}
