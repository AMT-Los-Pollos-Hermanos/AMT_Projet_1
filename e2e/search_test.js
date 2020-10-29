Feature('search');

Scenario('test search title', ({ I }) => {
    I.amOnPage('/')
    I.fillField('s', 'question 2')
    I.click('Rechercher')
    I.see('Question 2')
    I.see('Contenu 2')
    I.dontSee('Question 1')
    I.dontSee('Contenu 1')
    I.dontSee('Question 3')
    I.dontSee('Contenu 3')
});

Scenario('test search author', ({ I }) => {
    I.amOnPage('/')
    I.fillField('s', 'gil')
    I.click('Rechercher')
    I.see('Question 1')
    I.see('Contenu 1')
    I.dontSee('Question 2')
    I.dontSee('Contenu 2')
    I.dontSee('Question 3')
    I.dontSee('Contenu 3')
});
