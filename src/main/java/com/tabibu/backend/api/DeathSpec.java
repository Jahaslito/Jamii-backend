package com.tabibu.backend.api;

import com.tabibu.backend.models.Death;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "healthCareProvider.id", params = "healthCareProviderId", spec = Equal.class),
        @Spec(path = "disease.id", params = "diseaseId", spec = Equal.class),
        @Spec(path = "deathDate", params = "dateFrom", spec = GreaterThanOrEqual.class),
        @Spec(path = "deathDate", params = "dateTo", spec = LessThanOrEqual.class)
})
interface DeathSpec extends Specification<Death> { }