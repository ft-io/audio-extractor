package io.ft.audioextractor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Set;

@Component
public class HttpInterfaceFactoryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Set<BeanDefinition> beanDefinitions = new HttpInterfaceClassFinder()
                .findBeanDefinitions(beanFactory.getBean(Environment.class));

        HttpInterfaceFactory httpInterfaceFactory = new HttpInterfaceFactory();

        beanDefinitions.stream()
                .filter(v -> StringUtils.hasText(v.getBeanClassName()))
                .forEach(v -> findClassAndRegisterAsSingletonBean(beanFactory, httpInterfaceFactory, v));
    }

    private void findClassAndRegisterAsSingletonBean(
            ConfigurableListableBeanFactory beanFactory,
            HttpInterfaceFactory factory,
            BeanDefinition beanDefinition) {

        beanFactory.registerSingleton(
                beanDefinition.getBeanClassName(),
                factory.create(findHttpInterfaceClass(beanDefinition))
        );
    }

    private Class<?> findHttpInterfaceClass(BeanDefinition beanDefinition) {
        try {
            return ClassUtils.forName(beanDefinition.getBeanClassName(), this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
