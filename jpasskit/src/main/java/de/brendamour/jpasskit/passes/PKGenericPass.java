/**
 * Copyright (C) 2018 Patrice Brend'amour <patrice@brendamour.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.brendamour.jpasskit.passes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;

import de.brendamour.jpasskit.IPKValidateable;
import de.brendamour.jpasskit.PKField;

public class PKGenericPass implements IPKValidateable {

    private static final long serialVersionUID = 3408389190364251557L;

    protected List<PKField> headerFields;
    protected List<PKField> primaryFields;
    protected List<PKField> secondaryFields;
    protected List<PKField> auxiliaryFields;
    protected List<PKField> backFields;

    public void addPrimaryField(PKField field) {
        if (primaryFields == null) {
            primaryFields = new CopyOnWriteArrayList<PKField>();
        }
        primaryFields.add(field);
    }

    public List<PKField> getPrimaryFields() {
        return primaryFields;
    }

    public void setPrimaryFields(final List<PKField> primaryFields) {
        this.primaryFields = primaryFields;
    }

    public void addSecondaryField(PKField field) {
        if (secondaryFields == null) {
            secondaryFields = new CopyOnWriteArrayList<PKField>();
        }
        secondaryFields.add(field);
    }

    public List<PKField> getSecondaryFields() {
        return secondaryFields;
    }

    public void setSecondaryFields(final List<PKField> secondaryFields) {
        this.secondaryFields = secondaryFields;
    }

    public void addAuxiliaryField(PKField field) {
        if (auxiliaryFields == null) {
            auxiliaryFields = new CopyOnWriteArrayList<PKField>();
        }
        auxiliaryFields.add(field);
    }

    public List<PKField> getAuxiliaryFields() {
        return auxiliaryFields;
    }

    public void setAuxiliaryFields(final List<PKField> auxiliaryFields) {
        this.auxiliaryFields = auxiliaryFields;
    }

    public void addBackField(PKField field) {
        if (backFields == null) {
            backFields = new CopyOnWriteArrayList<PKField>();
        }
        backFields.add(field);
    }

    public List<PKField> getBackFields() {
        return backFields;
    }

    public void setBackFields(final List<PKField> backFields) {
        this.backFields = backFields;
    }

    public void addHeaderField(PKField field) {
        if (headerFields == null) {
            headerFields = new CopyOnWriteArrayList<PKField>();
        }
        headerFields.add(field);
    }

    public List<PKField> getHeaderFields() {
        return headerFields;
    }

    public void setHeaderFields(final List<PKField> headerFields) {
        this.headerFields = headerFields;
    }

    public boolean isValid() {
        return getValidationErrors().isEmpty();
    }

    public List<String> getValidationErrors() {

        List<String> validationErrors = new ArrayList<String>();

        List<List<PKField>> lists = Lists.newArrayList();
        lists.add(primaryFields);
        lists.add(secondaryFields);
        lists.add(headerFields);
        lists.add(backFields);
        lists.add(auxiliaryFields);

        for (List<PKField> list : lists) {
            if (list != null) {
                for (PKField pkField : list) {
                    if (!pkField.isValid()) {
                        validationErrors.addAll(pkField.getValidationErrors());
                    }
                }
            }
        }

        return validationErrors;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
