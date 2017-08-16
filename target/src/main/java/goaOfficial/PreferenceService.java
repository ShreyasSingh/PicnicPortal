package goaOfficial;

import java.util.List;

public class PreferenceService {

	private PreferenceRepository preferenceRepository;

	public PreferenceService(PreferenceRepository preferenceRepository) {
		this.preferenceRepository = preferenceRepository;
	}

	public List<Preference> getAllPreference() {
		return preferenceRepository.findAll();
	}

	public void savePreference(Preference preference) {
		preferenceRepository.save(preference);
	}

	public void updatePreference(Preference preference) {
		preferenceRepository.updatePreference(preference);
	}

	public void deleteAllPreferences() {
		preferenceRepository.deleteAll();
	}

	public void deleteSinglePreference(int iD) {
		preferenceRepository.delete(iD);
	}

	public Preference getPreferenceForEmployee(int companyID) {
		Preference preference = preferenceRepository.getPreferenceForEmployee(companyID);
		return preference;
	}

}
