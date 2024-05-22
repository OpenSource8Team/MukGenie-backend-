package org.example.mukgenie.decision_tree;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;

public class DecisionTreeExample {

    public static void main(String[] args) {
        try {
            // Load ARFF file
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File("FoodChoice.arff"));
            Instances data = loader.getDataSet();
            data.setClassIndex(data.numAttributes() - 1); // Set class index

            // Create and build decision tree
            J48 tree = new J48();
            tree.buildClassifier(data);

            // Print decision tree
            System.out.println(tree);

            // Evaluate decision tree
            Evaluation eval = new Evaluation(data);
            eval.evaluateModel(tree, data);
            System.out.println(eval.toSummaryString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
