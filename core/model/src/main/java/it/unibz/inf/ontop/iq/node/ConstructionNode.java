package it.unibz.inf.ontop.iq.node;

import com.google.common.collect.ImmutableSet;
import it.unibz.inf.ontop.iq.IntermediateQuery;
import it.unibz.inf.ontop.iq.exception.QueryNodeSubstitutionException;
import it.unibz.inf.ontop.iq.exception.QueryNodeTransformationException;
import it.unibz.inf.ontop.substitution.ImmutableSubstitution;
import it.unibz.inf.ontop.model.term.ImmutableTerm;
import it.unibz.inf.ontop.model.term.Variable;
import it.unibz.inf.ontop.iq.transform.node.HomogeneousQueryNodeTransformer;

import java.util.Optional;

/**
 * Head node an IntermediateQuery
 *
 * TODO: further explain
 *
 */
public interface ConstructionNode extends ExplicitVariableProjectionNode, UnaryOperatorNode {

    /**
     * (Some) projected variable --> transformed variable
     */
    ImmutableSubstitution<ImmutableTerm> getSubstitution();

    /**
     * TODO: explain
     */
    Optional<ImmutableQueryModifiers> getOptionalModifiers();

    @Override
    ConstructionNode clone();

    @Override
    ConstructionNode acceptNodeTransformer(HomogeneousQueryNodeTransformer transformer)
            throws QueryNodeTransformationException;

    /**
     * Variables that have to be provided by the child
     */
    ImmutableSet<Variable> getChildVariables();

    /**
     * TODO: find a better name and a better explanation.
     *
     * Equivalent to the regular substitution.
     * All projected variables that are defined from other variables
     * defined in the ancestor nodes have a explicit binding to them
     * in this substitution.
     *
     * In the regular substitution, they could just be bound to another
     * projected variable (INDIRECT).
     *
     */
    ImmutableSubstitution<ImmutableTerm> getDirectBindingSubstitution();

    @Override
    SubstitutionResults<ConstructionNode> applyAscendingSubstitution(
            ImmutableSubstitution<? extends ImmutableTerm> substitution,
            QueryNode childNode, IntermediateQuery query);

    @Override
    SubstitutionResults<ConstructionNode> applyDescendingSubstitution(
            ImmutableSubstitution<? extends ImmutableTerm> substitution, IntermediateQuery query)
            throws QueryNodeSubstitutionException;
}
