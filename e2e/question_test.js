Feature('question');

Scenario('test add question', ({I, login}) => {
    login('user')
    I.amOnPage('/questions')
    I.fillField('Titre', 'Quelle est le sens de la vie ?')
    I.fillField('.ck.ck-content.ck-editor__editable', '42')
    I.click('Ajouter')
    I.seeInCurrentUrl('/questions')
    I.see('Quelle est le sens de la vie ?', '.card-header')
    I.see('42', '.card-body')
});
