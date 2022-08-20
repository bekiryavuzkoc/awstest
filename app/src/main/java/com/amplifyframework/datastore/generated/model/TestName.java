package com.amplifyframework.datastore.generated.model;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.Objects;
import java.util.UUID;

/**
 * This is an auto generated class representing the TestName type in your schema.
 */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TestNames", authRules = {
        @AuthRule(allow = AuthStrategy.PUBLIC, operations = {ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ})
})
public final class TestName implements Model {
    public static final QueryField ID = field("id");
    public static final QueryField NAME = field("name");
    public static final QueryField AGE = field("age");
    private final @ModelField(targetType = "ID", isRequired = true)
    String id;
    private final @ModelField(targetType = "String", isRequired = true)
    String name;
    private final @ModelField(targetType = "Int")
    Integer age;
    private @ModelField(targetType = "AWSDateTime")
    Temporal.DateTime createdAt;
    private @ModelField(targetType = "AWSDateTime")
    Temporal.DateTime updatedAt;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Temporal.DateTime getCreatedAt() {
        return createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return updatedAt;
    }

    private TestName(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            TestName testName = (TestName) obj;
            return ObjectsCompat.equals(getId(), testName.getId()) &&
                    ObjectsCompat.equals(getName(), testName.getName()) &&
                    ObjectsCompat.equals(getAge(), testName.getAge()) &&
                    ObjectsCompat.equals(getCreatedAt(), testName.getCreatedAt()) &&
                    ObjectsCompat.equals(getUpdatedAt(), testName.getUpdatedAt());
        }
    }

    @Override
    public int hashCode() {
        return new StringBuilder()
                .append(getId())
                .append(getName())
                .append(getAge())
                .append(getCreatedAt())
                .append(getUpdatedAt())
                .toString()
                .hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("TestName {")
                .append("id=" + String.valueOf(getId()) + ", ")
                .append("name=" + String.valueOf(getName()) + ", ")
                .append("age=" + String.valueOf(getAge()) + ", ")
                .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
                .append("updatedAt=" + String.valueOf(getUpdatedAt()))
                .append("}")
                .toString();
    }

    public static NameStep builder() {
        return new Builder();
    }

    /**
     * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
     * This is a convenience method to return an instance of the object with only its ID populated
     * to be used in the context of a parameter in a delete mutation or referencing a foreign key
     * in a relationship.
     *
     * @param id the id of the existing item this instance will represent
     * @return an instance of this model with only ID populated
     */
    public static TestName justId(String id) {
        return new TestName(
                id,
                null,
                null
        );
    }

    public CopyOfBuilder copyOfBuilder() {
        return new CopyOfBuilder(id,
                name,
                age);
    }

    public interface NameStep {
        BuildStep name(String name);
    }


    public interface BuildStep {
        TestName build();

        BuildStep id(String id);

        BuildStep age(Integer age);
    }


    public static class Builder implements NameStep, BuildStep {
        private String id;
        private String name;
        private Integer age;

        @Override
        public TestName build() {
            String id = this.id != null ? this.id : UUID.randomUUID().toString();

            return new TestName(
                    id,
                    name,
                    age);
        }

        @Override
        public BuildStep name(String name) {
            Objects.requireNonNull(name);
            this.name = name;
            return this;
        }

        @Override
        public BuildStep age(Integer age) {
            this.age = age;
            return this;
        }

        /**
         * @param id id
         * @return Current Builder instance, for fluent method chaining
         */
        public BuildStep id(String id) {
            this.id = id;
            return this;
        }
    }


    public final class CopyOfBuilder extends Builder {
        private CopyOfBuilder(String id, String name, Integer age) {
            super.id(id);
            super.name(name)
                    .age(age);
        }

        @Override
        public CopyOfBuilder name(String name) {
            return (CopyOfBuilder) super.name(name);
        }

        @Override
        public CopyOfBuilder age(Integer age) {
            return (CopyOfBuilder) super.age(age);
        }
    }

}
