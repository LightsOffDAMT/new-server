package com.company.server.states.generators;

import com.company.server.states.annotations.RandomString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

@Component
public class GeneratorBeanPostProcessor implements BeanPostProcessor {
    private HashSet<String> usedIds = new HashSet<>();
    private Random random = new Random();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        final Class<?> beanClass = bean.getClass();
        Arrays.stream(beanClass.getDeclaredFields())
                .forEach(field ->{
                    if(field.isAnnotationPresent(RandomString.class)){
                        RandomString annotation = field.getAnnotation(RandomString.class);

                        StringBuilder resultString;
                        do {
                            resultString = new StringBuilder();
                            for(int i = 0; i < annotation.length(); i++)
                                resultString.append((char) (random.nextInt(('Z' - 'A' - 1)) + 'A'));
                        } while (usedIds.contains(resultString));
                        usedIds.add(resultString.toString());

                        boolean isAccessible = field.isAccessible();
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, bean, resultString.toString() );
                        field.setAccessible(isAccessible);
                    }
                });

        return bean;
    }
}
