package org.admissio.backend.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.admissio.backend.entity.Offer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OfferCustomRepositoryImpl implements OfferCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Offer> findAllByParams(Long majorId, Long regionId, Long universityId, String educationForm) {
        StringBuilder queryStr = new StringBuilder("SELECT o FROM Offer o WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (majorId != null) {
            queryStr.append(" AND o.major.id = ?1");
            params.add(majorId);
        }
        if (regionId != null) {
            queryStr.append(" AND o.university.universityRegion.id = ?").append(params.size() + 1);
            params.add(regionId);
        }
        if (universityId != null) {
            queryStr.append(" AND o.university.id = ?").append(params.size() + 1);
            params.add(universityId);
        }
        if (educationForm != null) {
            queryStr.append(" AND o.educationForm = ?").append(params.size() + 1);
            params.add(educationForm);
        }

        TypedQuery<Offer> query = entityManager.createQuery(queryStr.toString(), Offer.class);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }

        return query.getResultList();
    }
}
