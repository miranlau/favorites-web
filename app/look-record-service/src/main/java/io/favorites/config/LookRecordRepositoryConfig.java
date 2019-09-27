package io.favorites.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import io.favorites.domain.Collect;
import io.favorites.domain.LookRecord;
import io.favorites.domain.LookRecord2;
import io.favorites.domain.LookRecordBiz;

@Configuration
public class LookRecordRepositoryConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(LookRecord.class).exposeIdsFor(LookRecord2.class).exposeIdsFor(LookRecordBiz.class)
				.exposeIdsFor(Collect.class);
	}
}
