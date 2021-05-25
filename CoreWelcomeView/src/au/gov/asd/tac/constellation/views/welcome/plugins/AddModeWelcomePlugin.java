/*
 * Copyright 2010-2021 Australian Signals Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.gov.asd.tac.constellation.views.welcome.plugins;

import au.gov.asd.tac.constellation.graph.Graph;
import au.gov.asd.tac.constellation.graph.StoreGraph;
import au.gov.asd.tac.constellation.graph.file.opener.GraphOpener;
import au.gov.asd.tac.constellation.graph.locking.DualGraph;
import au.gov.asd.tac.constellation.graph.schema.Schema;
import au.gov.asd.tac.constellation.graph.schema.SchemaFactoryUtilities;
import au.gov.asd.tac.constellation.graph.schema.analytic.AnalyticSchemaFactory;
import au.gov.asd.tac.constellation.graph.schema.visual.concept.VisualConcept;
import au.gov.asd.tac.constellation.plugins.PluginInfo;
import au.gov.asd.tac.constellation.utilities.font.FontUtilities;
import au.gov.asd.tac.constellation.views.welcome.WelcomePluginInterface;
import au.gov.asd.tac.constellation.views.welcome.WelcomeTopComponent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.openide.util.NbBundle;

/**
 * The New Graph in Add Mode plugin for the Welcome Page.
 *
 * @author canis_majoris
 */

@PluginInfo(tags = {"WELCOME"})
@NbBundle.Messages("AddModeWelcomePlugin=Add Mode Welcome Plugin")
public class AddModeWelcomePlugin implements WelcomePluginInterface {
    
    private static final double TITLE_SIZE = 1.2;
    private static final int TEXT_SIZE = 10;
    public static final String NEW_GRAPH = "resources/welcome_add_graph.png";
    final ImageView addView = new ImageView(new Image(WelcomeTopComponent.class.getResourceAsStream(NEW_GRAPH)));
    final Button newButton = new Button();
        
    /**
     * Get a unique reference that is used to identify the plugin 
     *
     * @return a unique reference
     */
    @Override
    public String getName() {
        return "Add Graph Welcome";
    }
    
    /**
     * This method describes what action should be taken when the 
     * link is clicked on the Welcome Page
     *
     */
    @Override
    public void run() {
        final Schema schema = SchemaFactoryUtilities.getSchemaFactory(AnalyticSchemaFactory.ANALYTIC_SCHEMA_ID).createSchema();
        final StoreGraph sg = new StoreGraph(schema);
        schema.newGraph(sg);
        final int drawModeAttribute = VisualConcept.GraphAttribute.DRAWING_MODE.ensure(sg);
        sg.setBooleanValue(drawModeAttribute, 0, true);
        final Graph dualGraph = new DualGraph(sg, false);

        final String graphName = SchemaFactoryUtilities.getSchemaFactory(AnalyticSchemaFactory.ANALYTIC_SCHEMA_ID).getLabel().replace(" ", "").toLowerCase();
        GraphOpener.getDefault().openGraph(dualGraph, graphName);

    }

    /**
     * Determines whether this analytic appear on the Welcome Page 
     *
     * @return true is this analytic should be visible, false otherwise.
     */
    @Override
    public boolean isVisible() {
        return true;
    }
    
     /**
     * Creates the button object to represent this plugin
     * 
     * @return the button object
     */
    @Override
    public Button getButton(){
        addView.setFitHeight(75);
        addView.setFitWidth(75);
        final Label title = new Label("New Graph");
        title.setFont(new Font(FontUtilities.getApplicationFontFamily(), FontUtilities.getApplicationFontSize() * TITLE_SIZE));
        final Label subtitle = new Label("Add mode");
        subtitle.setFont(new Font(FontUtilities.getApplicationFontFamily(), TEXT_SIZE));
        final VBox layoutVBox = new VBox(addView, title, subtitle);
        layoutVBox.setAlignment(Pos.CENTER);
        newButton.setGraphic(layoutVBox);
        return newButton;
    }
}
