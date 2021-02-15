```java
    private final static ConcurrentHashMap<Class, JpaRepository> jpaDaoMap = new ConcurrentHashMap<>();

    public static <T, E> JpaRepository<T, E> getDomainRepository(Class<T> entityType) throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (jpaDaoMap.containsKey(entityType)) return jpaDaoMap.get(entityType);
        Map<String, Repository> beansOfType = applicationContext.getBeansOfType(Repository.class);
        for (Object daoBean : beansOfType.values()) {
            Method getTargetSourceMethod = daoBean.getClass().getMethod("getTargetSource");
            Object targetSource = getTargetSourceMethod.invoke(daoBean);

            Field targetField = targetSource.getClass().getDeclaredField("target");
            targetField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(targetField, targetField.getModifiers() & ~Modifier.FINAL);

            Object simpleJpaRepository = targetField.get(targetSource);
            Method getDomainClassMethod = simpleJpaRepository.getClass().getDeclaredMethod("getDomainClass");
            getDomainClassMethod.setAccessible(true);
            Class domainClass = (Class) getDomainClassMethod.invoke(simpleJpaRepository);

            if (domainClass.isAssignableFrom(entityType)) {
                jpaDaoMap.put(entityType, (JpaRepository) daoBean);
            } else {
                return null;
            }
        }
        return jpaDaoMap.get(entityType);
    }

```

