package com.tabibu.backend.api;

import com.tabibu.backend.models.Disease;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Or({
        @Spec(path = "name", params = "name", spec = Like.class),
        @Spec(path = "description", params = "name", spec = Like.class)
})
interface DiseaseSpec extends Specification<Disease> {
}