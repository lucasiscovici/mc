Servlet Xxx -> extends HttpServlet
			-> @override DoGet
				-> Appelle son Service associé (les Services commencent par un S suivi du nom de Servlet)
				-> new SXxx(HttpServletRequest req, HttpServletResponse resp)
				-> Cette appelle va appeler le Service associé et l'éxécuter, la reponse de cette appel sera dans la variable Resp du Service
				-> pour l'afficher on peux directement invoquer la methode print() au Service (new SXxx(HttpServletRequest req, HttpServletResponse resp).print())

service SXxx -> extends Service
			 -> @override giveGetEntry -> données d'entrées obligatoire pour le service (array of strings) (données sont dans params)
			 -> @override to_json -> données de sorties obligatoires pour le service (array of strings) (données sont dans Local_params)
			 -> @override koko -> methode general du service qui est appellé a sa construction
			 				->!!!! METTRE APRES UN RESP.C(t,e) UN RETURN; !!!!!
			 -> Convention -> Pour les Checks courant -> utiliser la class TestError (Check params, auth)
			 			   -> SORTIE EN JSON ->
			 			   					 -> SI ERREUR = {"code":"X",...}
			 			   					 -> SI SUCCESS = {"response":...}
			 			   					 -> SI RETOUR AVEC UNE SEUL LIGNE = {"response":{...}}
			 			   					 -> SI RETOUR AVEC PLUSIEURS LIGNES = {"response":[{},{},...]}
			 			   					 -> SI INSERTS -> {"response":{"id":"X"}}
			 			   					 -> SI DELETE(REMOVE)/UPDATE -> {"response":"OK"}
			 			   					 		-> CAS POUR TOUT SUPPRIMER (TYPE=ALL)
			 			   					 -> SI SELECT (GENERALEMENT AVEC UN SOUS DICTIONNAIRE JSON Y)
			 			   					 			 -> SI UNE LIGNE = {"response":{"Y":{...}}}
			 			   					 			 -> SI PLUSIEURS LIGNES = {"response":{"Y":[{},{},...]}}
			 			   					 -> !!!! IMPORTANT -> METTRE DANS Local_params les données dont on a besoin pour la sortie JSON !!!! ensuite on fait un RespS.cj(this);
			 			   -> NE PAS FAIRE DE TRAITEMENTS SUR LES PARAMS ENVOYÉS AU HELPERS (db_*_Helper)(on ne met que comme argument de la fct appellée: params (type Parameters) (CE SONT LES FCTS DES HELPERS QUI FERONT UN TRAITEMENT)
							
-> POUR CHAQUE PAQUAGE -> SAddX + SListX + SRemoveX
 SI SERVICE = SListX  -> 
						-> CAS AVEC ID DE LA ROW ( ENTRY GET  = ID)
			   			-> CAS POUR TOUT SELECTIONNER (ENTRY GET TYPE=ALL)
			 = SRemoveX -> 
						-> CAS AVEC ID DE LA ROW ( ENTRY GET  = ID)
						-> CAS POUR TOUT SELECTIONNER (ENTRY GET TYPE=ALL)
									
Service -> implements IOLUCAS, IParameters, TOJSON, ServiceKoko
		-> variable params -> Contient toutes les données d'entrées au commencement + d'autre si besoin pendant koko
		-> variable response DE IOLUCAS -> Pour les affichages or console
		-> variable getEntry -> arrays of strings des données obligatoire au commencement
		-> variable Local_params -> Contient toutes les données dont to_json a besoin pour créer la Response JSON
		-> variable RespS -> Réponse du Service -> error(ERREUR) ou response(SUCCESS) (voir SORTIE EN JSON)
		-> CONSTRUCTEURS() -> Tous les constructeurs vont directement ou indirectement affecter params, response et getEntry en fonctions de leurs parametres 
						 -> Appeler koko()
		-> print() -> fonctions permettant au service d'afficher sa RespS
		
Parameters -> la structure de donnée qui est utilisé en sortie et en entrée de fonctions 
		   -> Elle utilise une liste de Dico
		   -> Elle contient des fonctions qui facilise le traitement de données Parameters ou Dico
		   -> FCTS TRES UTILISÉES -> AddParam, getValue, getDico, AddParamResponse, CheckIfErrParams, PS, response, AddParamRespOK, responseID

Dico -> C'est une dictionaire (key:valeur) -> la valeur peut etre un string ou une liste de Dico ou rien
										   -> Il ne peux le pas y avoir de key ou une false key ( si on veux ne mettre qu'une valeur sans clé)
	 -> Contient des facilitants le traitement de données
	 -> FCTS TRES UTILISÉES -> vs_ak, response, toJSON, getValue, getKey, getValues,to_P, vT_toP

RespS -> Class pour la reponse du Service
Error -> Class pour les erreurs (code, description, detail)
TestError -> Class pour les Checks recurrent -> appelles des bonnes methodes des helpers + creation de la bonne resp avec la bonne erreur

db_*_Helper -> extend db ou dbM (MySQL, MongoDB)
			-> @override Insert
			-> @override Remove
			-> @override Update
			-> @override Select
			-> @override GiveMyTable -> On renseigne sa TABLE !!!
			-> Conventions 
						  -> On ecrit dans des variables toutes les strings dont on a besoin dans son helper ou cas ou elles changeraient en DB
						  -> On fait appelle a des fcts de sa superclass (db ou dbM) en priorité , mais sinon celle de db_Helper
						  -> POUR LES INSERTS -> On rajoute si Success, dont le params envoyé en parametre l'id de la Row inseré en DB ("id":X)
						  -> Si on doit faire un traitement particulier sur des params et qu'on veux  faire appelle a une methode de la superclass -> ON l'OVERRIDE
						  -> AVANT DE CHANGER DES CHOSES DANS UN params reçu en , on fait un copy(), ou on appelle un a fct qui fait une copy ou qui cree un nouveau Parameters directement ou indirectement

db -> Contient des fct recurente pour SQL (insert, remove, update, select, etcccc )	
dbM -> Contient des fct recurente pour MongoDB (insert, remove, update, select, etcccc )	
	-> id = _id = ObjectID

db_helper -> contient toutes les fonctions neccésaire pour excécuter du SQL ou du MongoDB 
		  -> Contient de fonctions qui créer les requetes 
		  -> toutes les entrées pour c'est fonctions si besoin sont des params (Parameters)
		  -> toutes les sorties si besoin sont des params (Parameters)
		  -> DELETE(REMOVE) SI COUNT OF REMOVE ROW == 0 RENVOI OK QD MM