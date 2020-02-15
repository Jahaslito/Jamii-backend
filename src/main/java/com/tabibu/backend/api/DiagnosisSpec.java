package com.tabibu.backend.api;

import com.tabibu.backend.models.Diagnosis;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Joins;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Joins({
        @Join(path= "diseases", alias = "disease")
})
@And({
        @Spec(path="disease.id", params="diseaseId", spec = Equal.class),
        @Spec(path = "healthCareProvider.id", params = "healthCareProviderId", spec = Equal.class),
        @Spec(path = "diagnosisDate", params = "dateFrom", spec = GreaterThanOrEqual.class),
        @Spec(path = "diagnosisDate", params = "dateTo", spec = LessThanOrEqual.class)
})
interface DiagnosisSpec extends Specification<Diagnosis> {
}
