package wallet.policies;

import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PolicyBeanPostProcessor implements BeanDefinitionRegistryPostProcessor {
    
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        var reflections = new Reflections("");
        var classesWithPolicy = reflections.getTypesAnnotatedWith(Policy.class);
        
        for(var clazz : classesWithPolicy) {
            var beanName = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
            
            var beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(clazz);
            beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
            
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

}
